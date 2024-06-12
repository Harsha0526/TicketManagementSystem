

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.growfin.ticketingSystem.models.payloads.*;
import com.growfin.ticketingSystem.security.JwtTokenProvider;
import com.growfin.ticketingSystem.services.*;

import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/org/signup")
    public ResponseEntity<?> registerOrganization(@Valid @RequestBody OrganizationRequest organizationRequest) {
        try {
            Organization organization = organizationService.createOrganization(organizationRequest);
            return new ResponseEntity<>(organization, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during organization signup.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<?> registerAdministrator(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            Administrator administrator = administratorService.createAdministrator(signUpRequest);
            return new ResponseEntity<>(administrator, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during administrator signup.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/admin/signin")
    public ResponseEntity<?> authenticateAdministrator(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Optional<String> jwt = administratorService.authenticateAdministrator(loginRequest);
            if (jwt.isPresent()) {
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt.get()));
            } else {
                return new ResponseEntity<>("Invalid email or password.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during administrator authentication.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
