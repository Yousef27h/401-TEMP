package com.asac.security.web;

import com.asac.security.domain.Department;
import com.asac.security.domain.User;
import com.asac.security.infrastructure.DepartmentRepository;
import com.asac.security.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("department", userRepository.findUserByUsername(userDetails.getUsername()).getDepartment().getName());
        return "profile";
    }

    @GetMapping("/department")
    public String getDepartmentPage(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());

        return "department";
    }

    @PostMapping("/department")
    public RedirectView addDepartment(@RequestParam String department) {
        departmentRepository.save(new Department(department));

        return new RedirectView("/department");
    }

    @PostMapping("/signup")
    public RedirectView attemptSignUp(@RequestParam String username, @RequestParam String password, @RequestParam Long department) {
        User newUser = new User(username, encoder.encode(password));
        newUser.setDepartment(departmentRepository.findById(department).orElseThrow());
        newUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, newUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        return "/403";
    }
}
