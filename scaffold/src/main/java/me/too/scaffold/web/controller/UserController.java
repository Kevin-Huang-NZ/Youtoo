package me.too.scaffold.web.controller;

import static me.too.scaffold.util.CheckUtil.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.too.scaffold.core.model.User;
import me.too.scaffold.core.service.UserService;
import me.too.scaffold.error.BusinessException;
import me.too.scaffold.error.EmBusinessError;

import com.github.pagehelper.Page;

import me.too.scaffold.web.param.CommonRequestParam;
import me.too.scaffold.web.param.IdWrap;
import me.too.scaffold.web.response.ListData;
import me.too.scaffold.web.response.PaginationInfo;
import me.too.scaffold.web.response.Root;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@GetMapping(value = { "/l" })
	public Root list(CommonRequestParam crp, User criteria) throws BusinessException {
		Page<User> searchResult = this.userService.selectPaged(crp, criteria);

		ListData<User> wrapper = new ListData<User>();
		wrapper.setDataList(searchResult.getResult()); 

		PaginationInfo paginationInfo = convertPage(searchResult);
		wrapper.setPagination(paginationInfo);

		return Root.create(wrapper);
	}

	@GetMapping(value = { "/r" })
	public Root read(Long id) throws BusinessException {

		User user = this.userService.selectById(id);
		if (user == null) {
			throw new BusinessException(EmBusinessError.DATA_NOT_EXIST);
		}

		return Root.create(user);
	}
	
	@PostMapping(value = { "/c" })
	public Root create(@RequestBody User user) throws BusinessException {
		this.checkCreate(user);

		int result = this.userService.save(user, authContext.get());

		return Root.create(result);
	}

	@PostMapping(value = { "/u" })
	public Root update(@RequestBody User user) throws BusinessException {
		this.checkUpdate(user);

		int result = this.userService.update(user, authContext.get());

		return Root.create(result);
	}

	@PostMapping(value = { "/d" })
	public Root delete(@RequestBody IdWrap id) throws BusinessException {
		int result = this.userService.delete(id.getId(), authContext.get());
		return Root.create(result);
	}
	
	private void checkCreate(User user) throws BusinessException {
		if (user == null) {
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"???????????????");
		}
		
		if (isEmpty(user.getUserName())) {
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"?????????????????????");
		}
		if (overLength(user.getUserName(), 50)) {
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"?????????????????????50????????????");
		}
		if (!patternCheck(PATTERN_MOBILE, user.getPhone())) {
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"????????????????????????????????????");
		}
		if (!patternCheck(PATTERN_PASSWORD, user.getPassword())) {
			throw new BusinessException(
			    EmBusinessError.PARAMETER_VALIDATION_ERROR,"????????????????????????????????????????????????????????????8???20??????(??????)???");
		}
		if (isEmpty(user.getRoles())) {
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"?????????????????????");
		}
	}

	
	private void checkUpdate(User user) throws BusinessException {
		if (user == null) {
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"???????????????");
		}
		if (user.getUserName() != null) {
			if (isEmpty(user.getUserName())) {
				throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"?????????????????????");
			}
			if (overLength(user.getUserName(), 50)) {
				throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"?????????????????????50????????????");
			}
		}
		if (user.getPhone() != null) {
			if (!patternCheck(PATTERN_MOBILE, user.getPhone())) {
				throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"????????????????????????????????????");
			}
		}
		if (user.getPassword() != null) {
			if (!patternCheck(PATTERN_PASSWORD, user.getPassword())) {
				throw new BusinessException(
				    EmBusinessError.PARAMETER_VALIDATION_ERROR,"????????????????????????????????????????????????????????????8???20??????(??????)???");
			}
		}
		if (user.getRoles() != null) {
			if (isEmpty(user.getRoles())) {
				throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"?????????????????????");
			}
		}
		if (user.getAvatar() != null) {
			if (isEmpty(user.getAvatar())) {
				throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"??????????????????");
			}
		}
	}
}
