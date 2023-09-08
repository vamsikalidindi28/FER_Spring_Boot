package com.rs.fer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rs.fer.entity.Address;
import com.rs.fer.entity.Expense;
import com.rs.fer.entity.User;
import com.rs.fer.repository.ExpenseRepository;
import com.rs.fer.repository.UserRepository;
import com.rs.fer.service.FERService;

@Service
public class FERServiceImpl implements FERService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExpenseRepository expenseRepository;

	// .........................................Registration..................................
	@Transactional
	@Override
	public boolean registration(User user) {
		return userRepository.save(user).getId() > 0;

	}

	// ...................................Login...............................................
	@Override
	public int login(String username, String password) {

		List<User> users=userRepository.findByUsernameAndPassword(username, password);
		if(users != null && !users.isEmpty()) {
			return users.get(0).getId();
			
		}
		return 0;	
	}

	// ....................... AddExpense..................................
	@Transactional
	@Override
	public boolean addExpense(Expense expense) {

		return expenseRepository.save(expense).getId() > 0;

	}

	// ....................... EditExpense..................................

	@Override
	public boolean editExpense(Expense expense) {

		return addExpense(expense);
	}

	// ..............................DeleteExpense............................

	@Override
	public boolean deleteExpense(int expenseid) {
		try {
			expenseRepository.deleteById(expenseid);
			return true;
		} catch (Exception e) {
			return false;

		}

	}

	// ...............................ResetPassword.................................
	@Override
	public boolean resetPassword(int userId, String currentPassword,String newPassword) {

		User user = getUser(userId);
		if (currentPassword.equals(user.getPassword())) {
			user.setPassword(newPassword);
			return registration(user);
		}
		return false;
	}

	// ..................GetExpenseOptions...................
	@Override
	public List<Expense> getExpenseOptions(int userid) {
		
		return expenseRepository.findByUserid(userid);
	}

	// ..........................................GetExpense.........................

	@Override
	public Expense getExpense(int expenseId) {
		return expenseRepository.findById(expenseId).get();
	}

	// ................ExpenseReport.......................
	@Override
	public List<Expense> expenseReport(int userid, String type, String fromDate, String toDate) {
		
		return expenseRepository.findByUseridAndTypeAndDateBetween(userid, type, fromDate, toDate);
		
	}

	// ........................................GetUser...........................................
	@Override
	public User getUser(int UserId) {

		Optional<User> optionalObject = userRepository.findById(UserId);
		if(optionalObject.isPresent()) {
			User user = optionalObject.get();
			if(user.getAddress()==null) {
				user.setAddress(new Address());
			}
			return user;
		}
		return null;
	}

	// .............................updateUser...........................
	@SuppressWarnings("resource")
	@Override

	public boolean updateUser(User user) {

		return registration(user);
	}
}