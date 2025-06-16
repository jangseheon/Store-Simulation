package store.store_simulation.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import store.store_simulation.DTO.UserDTO;
import store.store_simulation.Service.UserService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/api/auth/register")
    @ResponseBody
    public ResponseEntity<?> register(
            @RequestBody @Valid UserDTO dto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String,String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err ->
                    errors.put(err.getField(), err.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            userService.register(dto);
            return ResponseEntity.ok(Map.of("message","회원가입 성공"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()   .body(Map.of("error", e.getMessage()));
        }
    }
}
