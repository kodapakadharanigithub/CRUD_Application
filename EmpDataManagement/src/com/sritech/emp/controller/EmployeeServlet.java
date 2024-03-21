package com.sritech.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sritech.emp.Service.impl.EmployeeServiceImpl;
import com.sritech.emp.modelComponent.Employee;
import com.sritech.emp.serviece.EmployeeService;

@WebServlet("/")
public class EmployeeServlet extends HttpServlet {

	private EmployeeService employeeService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {

		employeeService = new EmployeeServiceImpl();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Entered into Controller");

		String action = request.getServletPath();

		System.out.println("Client Action = " + action);

		switch (action) {

		case "/new":
			viewNewEmployeeForm(request, response);
			break;

		case "/insert":

			employeeService.addNewEmployeeRecord(request, response);

			response.sendRedirect("list");

			break;

		case "/edit":
			viewEditForm(request, response);

			break;

		case "/update":

			boolean updateFlag = employeeService.updateEmployeeRecord(request, response);

			response.sendRedirect("list");
			break;

		case "/delete":

			employeeService.deleteEmployeeRecord(request, response);

			response.sendRedirect("list");

			break;
		default:

			List<Employee> employeesList = employeeService.employeesList();

			viewEmployeeList(employeesList, request, response);

		}

	}

	public void viewEmployeeList(List<Employee> employeesList, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			request.setAttribute("emplist", employeesList);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmployeeList.jsp");

			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void viewEditForm(HttpServletRequest request, HttpServletResponse response) {

		try {

			System.out.println("I am at viewEditForm");

			Employee employee = employeeService.getEmployeeRecord(request, response);

			request.setAttribute("emp", employee);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditEmployeeForm.jsp");

			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void viewNewEmployeeForm(HttpServletRequest request, HttpServletResponse response) {

		try {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddNewEmployeeForm.jsp");

			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
}
