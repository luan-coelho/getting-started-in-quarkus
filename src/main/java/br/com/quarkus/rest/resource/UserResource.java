package br.com.quarkus.rest.resource;

import br.com.quarkus.domain.model.User;
import br.com.quarkus.rest.resource.dto.UserCreateDTO;
import br.com.quarkus.rest.resource.dto.UserResponseDTO;
import br.com.quarkus.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/user")
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    ModelMapper mapper;

    @GET
    public Response getAll() {
        return Response.ok(userService.findAll()
                .stream()
                .map(user -> mapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList())
        ).build();
    }

    @Path("/{id}")
    @GET
    public Response getById(@PathParam("id") Long id) {
        User user = userService.findById(id);
        return Response.ok(mapper.map(user, UserResponseDTO.class)).build();
    }

    @POST
    public Response save(UserCreateDTO userRequest) {
        User userModel = mapper.map(userRequest, User.class);
        userService.save(userModel);
        return Response.ok().build();
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") Long id) {
        userService.deleteById(id);
        return Response.noContent().build();
    }
}