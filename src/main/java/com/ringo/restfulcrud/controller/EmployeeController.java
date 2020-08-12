package com.ringo.restfulcrud.controller;


import com.ringo.restfulcrud.dao.DepartmentDao;
import com.ringo.restfulcrud.dao.EmployeeDao;
import com.ringo.restfulcrud.entities.Department;
import com.ringo.restfulcrud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 *
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    /**
     * 获取全部员工信息
     * @param model
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    /**
     * 跳转到添加员工页面
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("保存的员工信息：" + employee);
        employeeDao.save(employee);
        // 重定向到员工列表页面
        return "redirect:/emps";
    }


    /**
     * 跳转到编辑页面，回显员工数据
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEditEmp(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        // 显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的员工信息：" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 删除员工信息
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }


}
