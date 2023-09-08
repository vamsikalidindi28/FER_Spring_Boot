package com.rs.fer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.fer.entity.Expense;
import com.rs.fer.entity.User;
import com.rs.fer.service.FERService;

@RestController
public class FERController {

	@Autowired
	private FERService ferService;

	// -------------------------------Registration-------------------------------

	@PostMapping(value = "/Registration")
	public ResponseEntity<Boolean> Registration(@RequestBody User user) {

		boolean isRegister = ferService.registration(user);
		if (isRegister) {
			return new ResponseEntity<Boolean>(isRegister, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(isRegister, HttpStatus.OK);

		}
	}

	// ---------------------------------------Login----------------------------------------

	@GetMapping("/login")
	public ResponseEntity<Integer> login(@RequestParam String username, @RequestParam String password) {

		int UserId = ferService.login(username, password);

		// 3 display
		if (UserId > 0) {
			return new ResponseEntity<Integer>(UserId, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(UserId, HttpStatus.NOT_FOUND);

		}
	}

	// ---------------------------------AddExpense------------------------------------------

	@PostMapping(value = "/addExpense")
	public ResponseEntity<Boolean> addExpense(@RequestBody Expense expense) {

		boolean isAddExpense = ferService.addExpense(expense);

		if (isAddExpense) {
			return new ResponseEntity<Boolean>(isAddExpense, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(isAddExpense, HttpStatus.NOT_FOUND);
		}

	}

	// -------------------------------------EditExpense----------------------------------------

	@GetMapping(value = "/EditExpenseOptions/{userid}")
	public ResponseEntity<List<Expense>> EditExpenseOptions(@PathVariable("userid") int userid) {

		List<Expense> expenseOptions = ferService.getExpenseOptions(userid);

		if (expenseOptions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {
			return new ResponseEntity<>(expenseOptions, HttpStatus.OK);

		}
	}

	@GetMapping(value = "/showEditExpense/{expenseid}")
	public ResponseEntity<Expense> EditExpense(@PathVariable("expenseid") int expenseid) {

		Expense editExpense = ferService.getExpense(expenseid);

		if (editExpense != null) {
			return new ResponseEntity<>(editExpense, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	@PutMapping(value = "/editExpense")
	public ResponseEntity<Boolean> editExpense(@RequestBody Expense expense) {

		boolean isEditExpense = ferService.editExpense(expense);

		if (isEditExpense) {
			return new ResponseEntity<Boolean>(isEditExpense, HttpStatus.OK);

		} else {
			return new ResponseEntity<Boolean>(isEditExpense, HttpStatus.NOT_FOUND);

		}

	}

//----------------------------------------DeleteExpense-----------------------------------

	@GetMapping(value = "/DeleteExpenseOptions/{userid}")
	public ResponseEntity<List<Expense>> DeleteExpenseOptions(@PathVariable("userid") int userid) {

		List<Expense> expenseOptions = ferService.getExpenseOptions(userid);

		if (expenseOptions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {
			return new ResponseEntity<>(expenseOptions, HttpStatus.OK);

		}
	}

	@DeleteMapping(value = "/DeleteExpense/{expenseid}")
	public ResponseEntity<Boolean> DeleteExpense(@PathVariable("expenseid") int expenseid) {

		boolean isDeleteExpense = ferService.deleteExpense(expenseid);

		if (isDeleteExpense) {
			return new ResponseEntity<Boolean>(isDeleteExpense, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(isDeleteExpense, HttpStatus.NOT_FOUND);
		}
	}

//----------------------------------ResetPassword--------------------------------------------

	@PutMapping(value = "/ResetPassword")
	public ResponseEntity<Boolean> ResetPassword(@RequestParam int userId, @RequestParam String currentPassword,
			@RequestParam String newPassword) {

		boolean isResetpassword = ferService.resetPassword(userId, currentPassword, newPassword);

		if (isResetpassword) {
			return new ResponseEntity<>(isResetpassword, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(isResetpassword, HttpStatus.NOT_FOUND);

		}

	}

	// --------------------------------ExpenseReport---------------------------------------------

	@GetMapping(value = "/ExpenseReport")
	public ResponseEntity<List<Expense>> ExpenseReport(@RequestParam int userId, @RequestParam String expenseType,
			@RequestParam String fromDate, @RequestParam String toDate) {

		List<Expense> expenseReport = ferService.expenseReport(userId, expenseType, fromDate, toDate);
		if (userId > 0) {
			return new ResponseEntity<>(expenseReport, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(expenseReport, HttpStatus.NOT_FOUND);

		}

	}

	// --------------------------------Update
	// Profile-----------------------------------

	@GetMapping(value = "/getUser/{userId}")
	public ResponseEntity<User> getUser(@PathVariable int userId) {
		User getUser = ferService.getUser(userId);
		if (getUser != null) {
			return new ResponseEntity<>(getUser, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	@PutMapping(value = "/updateProfile/{userid}")
	public ResponseEntity<Boolean> updateProfile(@PathVariable("userid") int userid, @RequestBody User user) {

		boolean isUserUpdate = ferService.updateUser(user);

		if (isUserUpdate) {
			return new ResponseEntity<Boolean>(isUserUpdate, HttpStatus.OK);

		} else {
			return new ResponseEntity<Boolean>(isUserUpdate, HttpStatus.NOT_FOUND);

		}

	}

}
