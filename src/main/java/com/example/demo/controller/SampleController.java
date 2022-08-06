package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.common.util.Util;
import com.example.demo.form.RegisterForm;
import com.example.demo.service.ModelService;
import com.example.demo.service.RecordListService;
import com.example.demo.service.RecordService;
import com.example.demo.service.RegisterService;
import com.example.demo.serviceImple.UserDetailsImpl;

@Controller
@RequestMapping("/")
public class SampleController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private RecordListService recordListService;
    @Autowired
    private ModelService modelService;

	@GetMapping
    public String index (@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
    	modelService.setModelFromIndex(model, userDetails, Util.getNowLocalDate());
    	return "top";
    }

    private void setAccountBaseData(Model model, UserDetailsImpl userDetails) {
    	model.addAttribute("employeeCode", userDetails.getUsername());
    	model.addAttribute("employeeName", userDetails.getEmployeeName());
	}

	@GetMapping("/login")
    public String login() {
    	return "login";
    }

    @GetMapping("/top")
    public String top() {
        return "top";
    }

    @PostMapping("/goToWork")
    public String goToWork(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
    	LocalDateTime nowTime = Util.getNowLocalDateTime();

    	//Stringって参照渡しできたっけ？
    	String errorMsg = recordService.recordByStart(userDetails.getUsername(), nowTime);

    	setAccountBaseData(model, userDetails);
    	model.addAttribute("errorMsg", errorMsg);
    	if (errorMsg == null) {
    		model.addAttribute("isRecordedByStart", true);
    		model.addAttribute("recordedTime", Util.formatHHmmss(nowTime));
    	}
    	model.addAttribute("isRecordedByEnd", false);

    	//modelからのデータがビューに反映されない
    	//return "redirect:/top";
    	return "/top";
    }

    @PostMapping("/leaveWork")
    public String leaveWork(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
    	LocalDateTime nowTime = Util.getNowLocalDateTime();
    	
    	//Stringって参照渡しできたっけ？
    	String errorMsg = recordService.recordByEnd(userDetails.getUsername(), nowTime);
    	
    	setAccountBaseData(model, userDetails);
    	model.addAttribute("recordTime", nowTime.toString());
    	model.addAttribute("errorMsg", errorMsg);
    	model.addAttribute("isRecordedByStart", errorMsg == null);
    	model.addAttribute("isRecordedByEnd", errorMsg == null);
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    	model.addAttribute("recordedTime", nowTime.format(formatter));
    	
    	//modelからのデータがビューに反映されない
    	//return "redirect:/top";
    	return "/top";
    }

    // 登録画面
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/register")
    public String register(Model model) {
        registerService.setModelByRegistering(model); // setModelBy + 関数名 + ing

        return "register";
    }

    // 登録画面の POST 受け付け
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    public String postRegister(@ModelAttribute @Valid RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // エラーがある場合は、エラーメッセージを表示したいので
            // View をレンダリングする。
            return "register";
        }
        
        registerService.createUser(registerForm.getName(), registerForm.getPassword(),
            registerForm.getRoleCode());

        return "redirect:/register";
    }

    @GetMapping("/attendanceRecord")
    public String attendanceRecord(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) throws Exception {
    	LocalDate nowDate = Util.getNowLocalDate();
    	LocalDate fromDate = LocalDate.of(nowDate.getYear(), nowDate.getMonth(), 1);
    	LocalDate toDate = fromDate.plusMonths(1).minusDays(1);
    	recordListService.setRecordDtos(model, userDetails, fromDate, toDate);
    	
        return "attendanceRecord";
    }
}