package com.saber.sample_service_provider.controllers;

import com.saber.sample_service_provider.dto.HelloDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(value = "${service.api.base-path}/hello", produces = MediaType.APPLICATION_JSON)
@Tag(name = "hello", description = "hello service")
@Slf4j
public class HelloController {


    @GetMapping("/sayHello")
    @Operation(summary = "sayHello", description = "sayHello",
            parameters = {
                    @Parameter(name = "firstName", in = ParameterIn.QUERY, required = true, example = "saber"),
                    @Parameter(name = "lastName", in = ParameterIn.QUERY, required = true, example = "Azizi")

            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HelloDto.class))})
            })
    public ResponseEntity<HelloDto> sayHello(@RequestParam String firstName, @RequestParam String lastName) {

        log.info("Request for say Hello with firstName {} , lastName {}", firstName, lastName);
        String message = String.format("Hello %s %s", firstName, lastName);
        HelloDto helloDto = new HelloDto();
        helloDto.setMessage(message);
        log.info("Response for say Hello with firstName {} , lastName {} ===> {}", firstName, lastName, helloDto);
        return ResponseEntity.status(HttpStatus.OK).body(helloDto);
    }

}
