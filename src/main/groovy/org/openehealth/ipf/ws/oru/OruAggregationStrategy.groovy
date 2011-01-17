package org.openehealth.ipf.ws.oru

import org.apache.camel.processor.aggregate.AggregationStrategy
import org.apache.camel.Exchange

/**
 * Strategy for aggregating responses from the XDS Repository and the GP Portal.
 *
 * @author Dmytro Rud
 */
class OruAggregationStrategy implements AggregationStrategy {

    /**
     * Converts exception object contained in the given exchange
     * into a single-element String array.
     */
    private static void normalizeExchange(Exchange exchange) {
        def exception = exchange.exception ?: exchange.properties.remove(Exchange.EXCEPTION_CAUGHT)
        exchange.exception = null
        if (exception) {
            String message = exception.message ?: exception.getClass().name
            exchange.in.body = [message]
        }
    }


    Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        normalizeExchange(newExchange)
        if (oldExchange) {
            ((List) newExchange.in.body).addAll(oldExchange.in.body)
        }
        return newExchange
    }
}
