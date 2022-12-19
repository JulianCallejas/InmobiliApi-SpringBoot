package inmobiliapi.controller;

import inmobiliapi.model.Contrato;
import inmobiliapi.model.ErrorResponse;
import inmobiliapi.service.ContratoAutonumberServiceImpl;
import inmobiliapi.service.ContratoServiceImpl;
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
@Tag(name = "contratos", description = "API para contratos")
public class ContratoController {

    @Autowired
    private ContratoServiceImpl contratoServiceImpl;

    @Autowired
    ContratoAutonumberServiceImpl contratoAutonumberServiceImpl;

    
    @Operation(summary = "Listar contratos", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Contrato.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/contratos", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAll() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (principal.toString().equals("usuario4@correo.com")) {
                return contratoServiceImpl.getAll();
            } else {
                ErrorResponse errorResponse = new ErrorResponse(401);
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            ErrorResponse errorResponse = new ErrorResponse(500);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @Operation(summary = "Buscar contrato por id", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Contrato.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/contratos/{idContrato}")
    public Object getByIdContrato(@PathVariable Long idContrato) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            Contrato contrato = contratoServiceImpl.getByIdContrato(idContrato);
            if (principal.toString().equals(contrato.getPropietario()) || principal.toString().equals(contrato.getArrendatario())) {
                return contrato;
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
    
    @Operation(summary = "Crear contrato", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Contrato.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/contratos")
    public Object createContrato(@RequestBody Contrato contrato) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (principal.toString().equals(contrato.getPropietario())) {

                if (contrato.camposRequeridos()) {
                    contrato.setIdContrato(contratoAutonumberServiceImpl.getAutonumber());
                    contratoServiceImpl.save(contrato);
                    return contrato;
                } else {
                    ErrorResponse errorResponse = new ErrorResponse(400);
                    errorResponse.setMessage(contrato.camposFaltantes());
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
    
    @Operation(summary = "Borrar contrato", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Contrato.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(value = "/contratos/{idContrato}")
    public Object deleteContrato(@PathVariable Long idContrato) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {

            Contrato contrato = contratoServiceImpl.getByIdContrato(idContrato);
            if (contrato == null) {
                ErrorResponse errorResponse = new ErrorResponse(400);
                errorResponse.setMessage("contrato no encontrado");
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
            if (principal.toString().equals(contrato.getPropietario())) {
                contratoServiceImpl.delete(contrato.getId());
                return contrato;
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
    
    @Operation(summary = "Editar contrato", description = "")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "ok",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(allOf = Contrato.class))})})
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping(value = "/contratos/{idContrato}")
    public Object editInmueble(@PathVariable Long idContrato, @RequestBody Contrato contrato) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            Contrato oldContrato = contratoServiceImpl.getByIdContrato(idContrato);
            if (oldContrato == null || contrato == null) {
                ErrorResponse errorResponse = new ErrorResponse(400);
                errorResponse.setMessage("contrato no encontrado");
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
            if (principal.toString().equals(oldContrato.getPropietario())) {

                if (contrato.camposRequeridos()) {
                    contrato.setId(oldContrato.getId());
                    contrato.setIdContrato(oldContrato.getIdContrato());
                    contratoServiceImpl.save(contrato);
                    return contrato;
                } else {
                    ErrorResponse errorResponse = new ErrorResponse(400);
                    errorResponse.setMessage(contrato.camposFaltantes());
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
