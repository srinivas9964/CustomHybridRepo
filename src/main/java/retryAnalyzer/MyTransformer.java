package retryAnalyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class MyTransformer implements IAnnotationTransformer {
	
	@Override
	public void transform(ITestAnnotation annotation, Class testclass, Constructor testContructor, Method testMethod)
	{
		try {
			annotation.setRetryAnalyzer(FailRetryAnalyzer.class);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
