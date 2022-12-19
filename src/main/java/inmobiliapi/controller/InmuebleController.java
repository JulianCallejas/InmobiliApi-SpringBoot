package inmobiliapi.controller;


import inmobiliapi.model.ErrorResponse;
import inmobiliapi.model.Inmueble;
import inmobiliapi.service.InmuebleAutonumberServiceImpl;
import inmobiliapi.service.InmuebleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Tag(name = "inmuebles", description = "API para inmuebles")
public class InmuebleController {

    @Autowired
    private InmuebleServiceImpl inmuebleServiceImpl;

    @Autowired
    private InmuebleAutonumberServiceImpl inmuebleAutonumberServiceImpl;
    
    @Operation(summary = "Listar inmuebles", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Inmueble.class))})})
    @GetMapping(value = "/inmuebles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Inmueble> getAll() {

        return inmuebleServiceImpl.getAll();
    }

    @Operation(summary = "Listar inmuebles disponibles", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Inmueble.class))})})
    @GetMapping(value = "/inmuebles/availables", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Inmueble> getAvailableInmuebles() {
        return inmuebleServiceImpl.getAvailableInmuebles();
    }
    
    @Operation(summary = "Buscar inmueble por id", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Inmueble.class))})})
    @GetMapping(value = "/inmuebles/{idInmueble}")
    public Inmueble getByEmail(@PathVariable Long idInmueble) {
        Inmueble inmueble = inmuebleServiceImpl.getByIdInmueble(idInmueble);
        return inmueble;

    }
    
    @Operation(summary = "Buscar inmueble por propietario", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Inmueble.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/inmuebles/propietario/{email}")
    public List<Inmueble> getByPropietario(@PathVariable String email) {
        return inmuebleServiceImpl.getInmueblesByPropietario(email);
    }
    
    @Operation(summary = "Buscar inmueble por arrendatario", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Inmueble.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/inmuebles/arrendatario/{email}")
    public List<Inmueble> getByArrendatario(@PathVariable String email) {
        return inmuebleServiceImpl.getInmueblesByArrendatario(email);
    }
    
    
    @Operation(summary = "Crear inmueble", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Inmueble.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole()")
    @PostMapping(value = "/inmuebles")
    public Object createInmueble(@RequestBody Inmueble inmueble) {
        try {
            if (inmueble.camposRequeridos()) {
                inmueble.setIdInmueble(inmuebleAutonumberServiceImpl.getAutonumber());
                inmuebleServiceImpl.save(inmueble);
                return inmueble;
            } else {
                ErrorResponse errorResponse = new ErrorResponse(400);
                errorResponse.setMessage(inmueble.camposFaltantes());
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            ErrorResponse errorResponse = new ErrorResponse(500);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @Operation(summary = "Borrar inmueble", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Inmueble.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole()")
    @DeleteMapping(value = "/inmuebles/{idInmueble}")
    public Object deleteInmueble(@PathVariable Long idInmueble) {
        try {
            Inmueble inmueble = inmuebleServiceImpl.getByIdInmueble(idInmueble);
            if (inmueble == null) {
                ErrorResponse errorResponse = new ErrorResponse(400);
                errorResponse.setMessage("Inmueble no encontrado");
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //Verifica el usuario autenticado
            if (principal.toString().equals(inmueble.getPropietario())) {
                inmuebleServiceImpl.delete(inmueble.getId());
                return inmueble;
            } else {
                ErrorResponse errorResponse = new ErrorResponse(401);
                return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            ErrorResponse errorResponse = new ErrorResponse(500);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @Operation(summary = "Editar inmueble", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Inmueble.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole()")
    @PatchMapping(value = "/inmuebles/{idInmueble}")
    public Object editInmueble(@PathVariable Long idInmueble, @RequestBody Inmueble inmueble) {
        try {
            Inmueble oldInmueble = inmuebleServiceImpl.getByIdInmueble(idInmueble);
            if (oldInmueble == null || inmueble == null) {
                ErrorResponse errorResponse = new ErrorResponse(400);
                errorResponse.setMessage("Inmueble no encontrado");
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //Verifica el usuario autenticado
            if (principal.toString().equals(oldInmueble.getPropietario()) && oldInmueble.getPropietario().equals(inmueble.getPropietario())) {
                if (inmueble.camposRequeridos()) {
                    inmueble.setId(oldInmueble.getId());
                    inmueble.setIdInmueble(oldInmueble.getIdInmueble());
                    inmuebleServiceImpl.save(inmueble);
                    return inmueble;
                } else {
                    ErrorResponse errorResponse = new ErrorResponse(400);
                    errorResponse.setMessage(inmueble.camposFaltantes());
                    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
                }
            } else {
                ErrorResponse errorResponse = new ErrorResponse(401);
                return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            ErrorResponse errorResponse = new ErrorResponse(500);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
