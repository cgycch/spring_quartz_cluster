package demo;

import static org.junit.Assert.assertNotNull;

import org.cch.component.BaseBean;
import org.cch.component.BaseBean3;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring_context.xml" })
@Transactional(transactionManager = "transactionManager", readOnly = true)
public class UnitTest {
	@BeforeClass
	public static void befClass() {
		System.out.println("@BeforeClass");
	}
	@Before
	public void bef() {
		System.out.println("@Before");
	}
	@After
	public void aft() {
		System.out.println("@After");
		System.out.println("");
	}
	@AfterClass
	public static void afterClass() {
		System.out.println("@AfterClass");
	}
	
	
	
	@Autowired
	private BaseBean bean;
	
	//@Resource(name="bb") //ok
	@Autowired //ok
	private BaseBean3 bean3;
	

	@Test
	public void test() {
		assertNotNull(bean);
		if(bean!=null) {
			System.out.println("test()");
		}
	}
	@Test
	public void test2() {
		assertNotNull(bean);
		if(bean!=null) {
			System.out.println("test2()");
		}
	}
	@Test
	public void test3() {
		assertNotNull(bean3);
		if(bean3!=null) {
			System.out.println("test3():name:"+bean3.getName());
		}
	}
}
