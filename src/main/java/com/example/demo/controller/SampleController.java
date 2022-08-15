package com.example.demo.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.util.JsonUtil;
import com.example.demo.common.util.Util;
import com.example.demo.dto.PortalDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.header.AttendanceRecordListHeaderDto;
import com.example.demo.service.AttendanceRecordListService;
import com.example.demo.service.ModelService;
import com.example.demo.service.RecordService;
import com.example.demo.service.RegisterService;
import com.example.demo.serviceImple.UserDetailsImpl;

@Controller
@RequestMapping("/")
@Validated
public class SampleController {
    
    @Autowired
    MessageSource messageSource;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private AttendanceRecordListService attendanceRecordListService;
    @Autowired
    private ModelService modelService;

	@GetMapping
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
    	modelService.setModelFromIndex(model, userDetails, Util.getNowLocalDate());
    	return "portal";
    }

	@GetMapping("/public/login")
    public String login() {
    	return "/public/login";
    }

    @GetMapping("/portal")
    public String portal() {
        return "portal";
    }

    @PostMapping("/goToWork")
    @ResponseBody
    public String goToWork(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
    	LocalDateTime nowTime = Util.getNowLocalDateTime();

    	//Stringって参照渡しできたっけ？
    	String errorMsg = recordService.recordByStart(userDetails.getUsername(), nowTime);

    	//return "redirect:/main";
    	PortalDto dto = PortalDto.fromByGoToWork(nowTime, errorMsg);
    	return JsonUtil.getJson(dto);
    }

    @PostMapping("/leaveWork")
    @ResponseBody
    public String leaveWork(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
    	LocalDateTime nowTime = Util.getNowLocalDateTime();
    	
    	String errorMsg = recordService.recordByEnd(userDetails.getUsername(), nowTime);

    	PortalDto dto = PortalDto.fromByLeaveWork(nowTime, errorMsg);
    	return JsonUtil.getJson(dto);
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
    public String postRegister(@ModelAttribute @Valid RegisterDto registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // エラーがある場合は、エラーメッセージを表示したいので
            // View をレンダリングする。
            return "register";
        }
        
        registerService.createUser(registerForm.getName(), registerForm.getPassword(),
            registerForm.getRoleCode());

        return "redirect:/register";
    }

    @GetMapping("/attendanceRecordList")
    public String attendanceRecordList(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) throws Exception {
//    	LocalDate nowDate = Util.getNowLocalDate();
//    	LocalDate fromDate = LocalDate.of(nowDate.getYear(), nowDate.getMonth(), 1);
//    	LocalDate toDate = fromDate.plusMonths(1).minusDays(1);
//    	attendanceRecordListService.setRecordDtos(model, userDetails, fromDate, toDate);
//    	model.addAttribute("employeeName", userDetails.getEmployeeName());

    	return "attendanceRecordList";
    }

    @GetMapping("/attendanceRecordListSearch")
    @ResponseBody
    public void attendanceRecordListSearch(@Validated  AttendanceRecordListHeaderDto headerDto) throws NumberFormatException {
    	int a = 0;
    }

    @PostMapping("/getResourceMessage")
    @ResponseBody
    public String note(@RequestParam String note) {
        return note + note;
    }
}