package com.guptaji.resources;

import org.acme.kafka.quarkus.Picture;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/picture")
public class MovieResource {

    @Inject
    @Channel("movies")
    Emitter<Picture> pictureEmitter;

    @POST
    public Response printMovieName(Picture movie){
        pictureEmitter.send(movie);
        System.out.println("movie title -------------- "+movie.getTitle());
        return Response.accepted().build();
    }
}
