package com.saber.sample_service_client.controllers;

import com.saber.sample_service_client.dto.*;
import com.saber.sample_service_client.services.GoRestClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(value = "${service.api.base-path}/goRestClient", produces = MediaType.APPLICATION_JSON)
@Tag(name = "goRest Client", description = "goRest Client service")
@Slf4j
@RequiredArgsConstructor
public class GoRestClientController {


    private final GoRestClientService goRestClientService;

    @GetMapping("/users")
    @Operation(summary = "users", description = "findAllUsersByPage",
            parameters = {
                    @Parameter(name = "page", in = ParameterIn.QUERY, required = false, example = "45")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "403", description = "FORBIDDEN", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            })
    public ResponseEntity<UserResponse> findAllUsersByPage(@RequestParam(required = false) Integer page) {
        UserResponse allUsers = this.goRestClientService.findAllUsers(page);
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/findUserById")
    @Operation(summary = "findUserById", description = "findUserById",
            parameters = {
                    @Parameter(name = "id", in = ParameterIn.QUERY, required = true, example = "34")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))}),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "403", description = "FORBIDDEN", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            })
    public ResponseEntity<UserDto> findUserById(@RequestParam(required = true) Long id) {
        UserDto user = this.goRestClientService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/findPostById")
    @Operation(summary = "findPostById", description = "findPostById",
            parameters = {
                    @Parameter(name = "id", in = ParameterIn.QUERY, required = true, example = "25")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))}),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "403", description = "FORBIDDEN", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            })
    public ResponseEntity<PostDto> findPostById(@RequestParam Long id) {
        PostDto post = this.goRestClientService.findPostsById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    @Operation(summary = "posts", description = "findAllPostsByPage",
            parameters = {
                    @Parameter(name = "page", in = ParameterIn.QUERY, required = false, example = "45")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "403", description = "FORBIDDEN", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
                    @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            })
    public ResponseEntity<PostResponse> findAllPostsByPage(@RequestParam(required = false) Integer page) {
        PostResponse allUsers = this.goRestClientService.findAllPosts(page);
        return ResponseEntity.ok(allUsers);
    }

}
