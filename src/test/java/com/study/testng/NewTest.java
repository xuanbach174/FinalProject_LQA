package com.study.testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class NewTest {
	
  int addNumbers (int a, int b) {
	  return a+b;
  }
	
  @Test
  public void tc01() {
	  System.out.println("this is tc 01");
  }
  
  @Test
  public void tc02() {
	  System.out.println("this is tc 02");
  }
  
  @Test
  public void tc03() {
	  System.out.println("this is tc 03");
  }
  
  @Test
  public void tc04() {
	  System.out.println("this is tc 04");
  }
  
  @Test
  public void tc05() {
	  System.out.println("this is tc 05");
  }
  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

}
