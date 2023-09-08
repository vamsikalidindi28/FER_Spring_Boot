package com.rs.fer.service;

import java.util.List;

import com.rs.fer.entity.Expense;
import com.rs.fer.entity.User;

public interface FERService {

	boolean registration(User user);

	int login(String username, String password);

	boolean addExpense(Expense expense);

	boolean editExpense(Expense expense);

	boolean deleteExpense(int expenseid);

	boolean resetPassword(int userid, String newPassword, String Currentpassword);

	List<Expense> getExpenseOptions(int userid);

	Expense getExpense(int expenseId);

	List<Expense> expenseReport(int userid, String type, String fromDate, String toDate);

	User getUser(int Userid);

	boolean updateUser(User user);

}
