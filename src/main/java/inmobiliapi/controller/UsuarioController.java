package inmobiliapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import inmobiliapi.model.ErrorResponse;
import inmobiliapi.model.Individuo;
import inmobiliapi.model.Usuario;
import inmobiliapi.model.UsuarioCompleto;
import inmobiliapi.service.UsuarioCompletoServiceImpl;
import inmobiliapi.service.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
@Tag(name = "usuarios", description = "API para usuarios")
public class UsuarioController {

    private Usuario loggedUser;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private UsuarioCompletoServiceImpl usuarioCompletoServiceImpl;

    @Autowired
    BCryptPasswordEncoder encoder;

    private void setloggedUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();  //Se puede usar esta linea para capturar el usuario autenticado
        if (principal.toString() != null) {
            this.loggedUser = usuarioServiceImpl.getByEmail(principal.toString());
        } else {
            this.loggedUser.setEmail("Sin Autenticar");
        }

    }

    @Operation(summary = "Crear usuario", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Individuo.class))})})
    @PostMapping(value = "/usuarios")
    public ResponseEntity<String> createUsuario(@RequestBody Usuario usuario) throws JsonProcessingException {
        JsonObject jsonRes = new JsonObject();
        try {
            if (usuario.getContrasena() == null
                    || usuario.getContrasena().isBlank()
                    || !usuario.validEmail()) {
                throw new Exception("Bad Request");
            }
//            usuario.setContrasena(encoder.encode(usuario.getContrasena()));
            Usuario nusuario = new Usuario();
            nusuario.setContrasena(encoder.encode(usuario.getContrasena()));
            nusuario.setEmail(usuario.getEmail());
            nusuario.setIdentificacion(usuario.getIdentificacion());
            nusuario.setNombre(usuario.getNombre());
            nusuario.setTelefono(usuario.getTelefono());

            Usuario nuevoUsuario = usuarioServiceImpl.save(nusuario);

            jsonRes.addProperty("email", nuevoUsuario.getEmail());
            jsonRes.addProperty("nombre", nuevoUsuario.getNombre());
            jsonRes.addProperty("identificacion", nuevoUsuario.getIdentificacion());
            jsonRes.addProperty("telefono", nuevoUsuario.getTelefono());

            return new ResponseEntity<>(jsonRes.toString(), HttpStatus.OK);
        } catch (Exception e) {
            jsonRes.addProperty("statusCode", 400);
            jsonRes.addProperty("message", "Datos Incorrectos");
            jsonRes.addProperty("error", "Bad Request");

            return new ResponseEntity<>(jsonRes.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Listar usuarios", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Individuo.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole()")
    @GetMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAll() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.toString().equals("usuario4@correo.com")) {
            return usuarioServiceImpl.getAll();
        } else {
            ErrorResponse errorResponse = new ErrorResponse(401);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Buscar usuario por email", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Individuo.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole()")
    @GetMapping(value = "/usuarios/{email}")
    public Object getByEmail(@PathVariable String email) {
        Usuario usuario = usuarioServiceImpl.getByEmail(email);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //Verifica el usuario autenticado
        if (principal.toString().equals(usuario.getEmail())) {
            return usuario;
        } else {
            ErrorResponse errorResponse = new ErrorResponse(401);
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }

//System.out.println(encoder.matches("1234", usuario.getContrasena()));
    }

    @Operation(summary = "Editar usuario", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Individuo.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping(value = "/usuarios/{email}")
    public ResponseEntity<String> editUsuario(@PathVariable String email, @RequestBody Usuario usuario) throws JsonProcessingException {
        setloggedUser();
        JsonObject jsonRes = new JsonObject();

        if (loggedUser.getEmail().equals(email)) {

            try {
                Usuario oldUsuario = usuarioServiceImpl.getByEmail(email);
                if (!oldUsuario.validEmail()) {
                    throw new Exception("Bad Request");
                }
                if (!(usuario.getContrasena() == null || usuario.getContrasena().length() == 0)) {
                    oldUsuario.setContrasena(encoder.encode(usuario.getContrasena()));
                }
                oldUsuario.setNombre(usuario.getNombre());
                oldUsuario.setIdentificacion(usuario.getIdentificacion());
                oldUsuario.setTelefono(usuario.getTelefono());
                usuarioServiceImpl.save(oldUsuario);

                jsonRes.addProperty("email", oldUsuario.getEmail());
                jsonRes.addProperty("nombre", oldUsuario.getNombre());
                jsonRes.addProperty("identificacion", oldUsuario.getIdentificacion());
                jsonRes.addProperty("telefono", oldUsuario.getTelefono());

                return new ResponseEntity<>(jsonRes.toString(), HttpStatus.OK);
            } catch (Exception e) {
                jsonRes.addProperty("statusCode", 400);
                jsonRes.addProperty("message", "Datos Incorrectos");
                jsonRes.addProperty("error", "Bad Request");

                return new ResponseEntity<>(jsonRes.toString(), HttpStatus.BAD_REQUEST);
            }
        } else {
            jsonRes.addProperty("statusCode", 401);
            jsonRes.addProperty("message", "No tiene Autorizacion");
            jsonRes.addProperty("error", "Unauthorized");
            return new ResponseEntity<>(jsonRes.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "Eliminar usuario", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Individuo.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(value = "/usuarios/{email}")
    public ResponseEntity<String> deleteUsuario(@PathVariable String email) throws JsonProcessingException {
        setloggedUser();
        JsonObject jsonRes = new JsonObject();

        if (loggedUser.getEmail() == null || loggedUser.getEmail().equals(email)) {

            try {
                Usuario usuario = usuarioServiceImpl.getByEmail(email);

                usuarioServiceImpl.delete(usuario.getId());
                jsonRes.addProperty("email", usuario.getEmail());
                jsonRes.addProperty("nombre", usuario.getNombre());
                jsonRes.addProperty("identificacion", usuario.getIdentificacion());
                jsonRes.addProperty("telefono", usuario.getTelefono());
                return new ResponseEntity<>(jsonRes.toString(), HttpStatus.OK);

            } catch (Exception e) {
                jsonRes.addProperty("statusCode", 500);
                jsonRes.addProperty("message", "No se pudo eliminar el usuario");
                jsonRes.addProperty("error", e.toString());

                return new ResponseEntity<>(jsonRes.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            jsonRes.addProperty("statusCode", 401);
            jsonRes.addProperty("message", "No tiene Autorizacion");
            jsonRes.addProperty("error", "Unauthorized");
            return new ResponseEntity<>(jsonRes.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "Editar usuario", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioCompleto.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/usuarios/usuariocompleto/{email}")
    public Object getUsuarioCompletoByEmail(@PathVariable String email) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //Verifica el usuario autenticado
        if (principal.toString().equals(email)) {
            try {
                UsuarioCompleto usuarioCompleto = usuarioCompletoServiceImpl.getByEmail(email);
                return usuarioCompleto;
            } catch (Exception e) {
                System.out.println(e.toString());
                ErrorResponse errorResponse = new ErrorResponse(500);
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            ErrorResponse errorResponse = new ErrorResponse(401);
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }

    }

}
