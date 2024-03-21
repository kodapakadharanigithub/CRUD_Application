package com.sritech.emp.Service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sritech.emp.dao.EmployeeDAO;
import com.sritech.emp.modelComponent.Employee;
import com.sritech.emp.serviece.EmployeeService;
import com.sritech.employee.DAO.impl.EmployeeDAOImpl;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO = null;

	public EmployeeServiceImpl() {

		employeeDAO = new EmployeeDAOImpl();
	}

	@Override
	public List<Employee> employeesList() {

		List<Employee> list = null;

		try {
			list = employeeDAO.getAllEmployees();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Employee getEmployeeRecord(HttpServletRequest request, HttpServletResponse response) {

		Employee employee = null;

		try {

			int empId = Integer.parseInt(request.getParameter("empId"));

			employee = employeeDAO.getEmployeeById(empId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;
	}

	@Override
	public boolean updateEmployeeRecord(HttpServletRequest request, HttpServletResponse response) {

		boolean updateFlag = false;
		try {

			Employee employee = new Employee();

			employee.setEmpId(Integer.parseInt(request.getParameter("empId")));

			employee.setEmpName(request.getParameter("empName"));

			employee.setEmpJob(request.getParameter("empJob"));

			employee.setEmpSalary(Float.parseFloat(request.getParameter("empSalary")));

			employee.setEmpDeptNum(Integer.parseInt(request.getParameter("empDeptNum")));

			employee.setEmpGender(request.getParameter("empGender"));

			employee.setEmpAge(Integer.parseInt(request.getParameter("empAge")));

			updateFlag = employeeDAO.updateEmployeeDetails(employee);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return updateFlag;
	}

	@Override
	public int addNewEmployeeRecord(HttpServletRequest request, HttpServletResponse response) {

		int count = 0;

		try {
			Employee employee = new Employee();

			employee.setEmpName(request.getParameter("empName"));

			employee.setEmpJob(request.getParameter("empJob"));

			employee.setEmpSalary(Float.parseFloat(request.getParameter("empSalary")));

			employee.setEmpDeptNum(Integer.parseInt(request.getParameter("empDeptNum")));

			employee.setEmpGender(request.getParameter("empGender"));

			employee.setEmpAge(Integer.parseInt(request.getParameter("empAge")));

			count = employeeDAO.insertEmployeeRecord(employee);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public int deleteEmployeeRecord(HttpServletRequest request, HttpServletResponse response) {

		int count = 0;
		try {

			int empId = Integer.parseInt(request.getParameter("empId"));

			count = employeeDAO.deleteEmployeeRecordById(empId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
