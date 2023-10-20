package uz.jurayev.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.jurayev.account.utils.Man;
import uz.jurayev.account.utils.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class AccountApplication {

	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		SpringApplication.run(AccountApplication.class, args);

		// Create the original object
		Man arnold = new Man("Xurshid", 30, "Thal", "Austria");

		// Get the class loader from the original object
		ClassLoader arnoldClassLoader = arnold.getClass().getClassLoader();

		// Get all the interfaces that the original object implements
		Class<?>[] interfaces = arnold.getClass().getInterfaces();

//		Method introduce = arnold.getClass().getMethod("introduce", String.class);
//		introduce.invoke(arnold, "Hello");

		// Create a proxy for our arnold object
		Person person = (Person) Proxy.newProxyInstance(arnoldClassLoader, interfaces, (proxy, method, args1) -> {
			System.out.println("Hi");
//			return method.invoke(arnold, args1);
			return null;
		});


		// Call one of our original object's methods on the proxy object
		person.introduce(arnold.getName());
//		person.sayAge(arnold.getAge());
	}

}
