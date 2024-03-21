package com.sritech.emp.serviece;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sritech.emp.modelComponent.Employee;

public interface EmployeeService {

	public abstract List<Employee> employeesList();

	public abstract Employee getEmployeeRecord(HttpServletRequest request, HttpServletResponse response);

	public abstract boolean updateEmployeeRecord(HttpServletRequest request, HttpServletResponse response);

	public abstract int addNewEmployeeRecord(HttpServletRequest request, HttpServletResponse response);

	public abstract int deleteEmployeeRecord(HttpServletRequest request, HttpServletResponse response);

}
