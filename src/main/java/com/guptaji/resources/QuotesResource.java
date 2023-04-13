package com.guptaji.resources;

import com.guptaji.entity.Quote;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/quotes")
public class QuotesResource {

    @Inject
    @Channel("quote-data-request")
    Emitter<String> quoteDataRequestEmitter;

//    @Channel("quotes")
//    Multi<Quote> quotesData;

    @POST
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    public String createRequest() {
        UUID uuid = UUID.randomUUID();
        quoteDataRequestEmitter.send(uuid.toString());
        return uuid.toString();
    }

    @Incoming("incomingTopic")
    public void recievedData(Quote quoteData){
        System.out.println(quoteData.getId()+"--------------"+quoteData.getPrice());
    }

//    @GET
//    @Produces(MediaType.SERVER_SENT_EVENTS)
//    public Multi<Quote> stream() {
//        System.out.println("quotes data ------------- "+ quotesData);
//        return quotesData;
//    }
}
