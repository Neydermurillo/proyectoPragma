package com.pragpooling.springboot.backend.apirest.controllers;

import com.pragpooling.springboot.backend.apirest.entity.User;
import com.pragpooling.springboot.backend.apirest.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserServiceImpl userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PostMapping("/users1")
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        User userNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            userNew = userService.save(user);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha sido creado con exito!");
        response.put("usuario", userNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @PutMapping("/users1/{id}")
    public ResponseEntity<?>  Update(@Valid @RequestBody User user,BindingResult result, @PathVariable Long id){

        User currentUser = userService.findById(id);

        User updatedUser = null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){

            List<String>errors = result.getFieldErrors()
                    .stream()
                    .map(err-> "El campo '"+err.getField()+"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (currentUser==null){
            response.put("mensaje","Error: no se pudo editar el usuario ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FOUND);
        }
        try {
            currentUser.setLastName(user.getLastName());
            currentUser.setFirstName(user.getFirstName());
            currentUser.setTelephoneNumber(user.getTelephoneNumber());
            currentUser.setAddress(user.getAddress());
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword(user.getPassword());

            updatedUser= userService.save(currentUser);

        } catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El usuario ha sido actualizado con exito!");
        response.put("usuario", updatedUser);

        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/users1/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try {
            userService.delete(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el usuario de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","Usuario  eliminado con exito!");
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }
}


