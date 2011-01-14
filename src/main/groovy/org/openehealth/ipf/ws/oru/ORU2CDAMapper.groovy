package org.openehealth.ipf.ws.oru


import org.openehealth.ipf.modules.cda.CDAR2Renderer
import org.eclipse.emf.ecore.xmi.XMLResource
import org.openehealth.ipf.modules.cda.builder.CDAR2Builder
import org.openehealth.ipf.modules.cda.builder.CDAR2ModelExtension
import java.nio.charset.Charset


/**
 * Created by IntelliJ IDEA.
 * User: 100026806
 * Date: 11.01.11
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */
class ORU2CDAMapper {
    def static builder;

    static {
      ExpandoMetaClass.enableGlobally()
      new CDAR2ModelExtension().extensions.call()
      builder = new CDAR2Builder();
    }

    public static String convertORUtoCDA(def message) {
      def cdaDoc = buildCDAHeader(message);
      def renderer = new CDAR2Renderer();
      def opts = [:]
      opts[XMLResource.OPTION_DECLARE_XML] = true
      opts[XMLResource.OPTION_ENCODING] = 'utf-8'
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      renderer.render(cdaDoc, baos, opts);
      String strDoc = new String(baos.toByteArray());
      return strDoc;
    }

    private static def buildCDAHeader(def message) {
      def repeatingGroup = message.PATIENT_RESULT;
      def group = repeatingGroup(0);
      def obsGroup = group.ORDER_OBSERVATION(0);
      def obrSeg = obsGroup.OBR;
      def orcSeg = obsGroup.ORC;
      def pidSeg = group.PATIENT.PID;
      def obxGroup = obsGroup.OBSERVATION(0);
      def obxSeg1 = obxGroup.OBX;
      def auth = null;
      if (obrSeg[16](0)[1]?.value != null) {
        auth = obrSeg[16](0);
      } else {
        auth = obxSeg1[16];
      }

      StringBuilder report = new StringBuilder();
      //TODO: Need to externalize this "title"
      report.append("Title: " + obrSeg[4][1]?.value + "-" + obrSeg[4][2]?.value + "\n");
      for(int i = 0; i < obsGroup.getOBSERVATIONReps(); i++) {
        def obx5 = obsGroup.getOBSERVATION(i).OBX[5];
        if (obx5?.value != null) {
          report.append(obx5?.value + "\n");
        }
      }

      def document = builder.build {
        clinicalDocument {

          // CDA Header
          templateId(root:'1.3.6.1.4.1.19376.1.2.21')
          typeId(root:'2.16.840.1.113883.1.3', extension:'POCD_HD000040')
          //TODO: Need to prefix this hospital domain
          id(root:'2.16.840.1.113883.3.933', extension:obrSeg[3][1])
          code(                                                 // 3
            code:'19005-8',
            codeSystem:'2.16.840.1.113883.6.1',
            codeSystemName:'LOINC',
            displayName:'Radiology Report'
          )
          //TODO: Need to remove constant value here
          languageCode(
            code: 'fr-FR'
          )
          effectiveTime(obrSeg[7][1]?.value)                             // 4
          title('Good Health Clinic Consultation Note')         // 5
          confidentialityCode('N')

          // Patient details
          recordTarget {
            patientRole {
              id(extension:pidSeg[3](1)[1] ?: '', root:pidSeg[3](1)[4][2])
              patient {
                name {
                  given(pidSeg[5](0)[2]?.value)
                  family(pidSeg[5](0)[1]?.value)
                }
                administrativeGenderCode(
                  code:pidSeg[8],
                  codeSystem:'2.16.840.1.113883.5.1'
                )
                birthTime(pidSeg[7][1].toString())
              }

              if (pidSeg[11](0) != null) {
                addr {
                  streetAddressLine(pidSeg[11](0)[1]?.value)
                  city(pidSeg[11](0)[3]?.value)
                  postalCode(pidSeg[11](0)[5]?.value)
                  country(pidSeg[11](0)[6]?.value)
                  state('')    //TODO: get actual value
                }
              }
            }
          }

          // Author details
          author {
            time(obrSeg[7]?.value)
            assignedAuthor {
              id(extension:auth[1]?.value, root:'2.16.840.1.113883.19.5')
              assignedPerson {
                 name {
                    given(auth[3]?.value)
                     family(auth[2]?.value)
                    suffix('') //TODO: get suffix if required
                  }
              }
              // TODO: Using ORC Segment, which needs to be included in HL7,
              // can't we get it from PID assigningAuthority?
              representedOrganization {
                id(extension:orcSeg[10][14][1]?.value ,root:'1.3.5.35.1.4436.7')
                name(orcSeg[10][14][2]);
              }
            }
          }

          //TODO: Need to add custodian details, which segment provides this info?
          custodian {
            assignedCustodian {
              representedCustodianOrganization {
                 id(root:auth[9][2]?.value)
                 name(auth[9][1]?.value)
              }
            }
          }

          //Create non-XMLBody in CDA, populate OBX segments
          component {
            nonXMLBody {
              text(
                    mediaType: 'text/plain',
                    representation: 'B64',
                    report.toString()
              )
            }
          }
        }
      }
      return document;
    }
}
