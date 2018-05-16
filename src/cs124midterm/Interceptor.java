package cs124midterm;

import java.io.Serializable;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.DynamicType.Loaded;
import net.bytebuddy.dynamic.DynamicType.Unloaded;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class Interceptor implements Serializable {
	public Object run(Class<? extends Object> clazz) throws Exception{
		ByteBuddy byteBuddy = new ByteBuddy();
		DynamicType.Builder<? extends Object> builder = byteBuddy.subclass(clazz).implement(EnterCondition.class);
		builder = builder.method(ElementMatchers.named("canEnter")).intercept(MethodDelegation.to(clazz));
		builder = builder.method(ElementMatchers.isAnnotatedWith(anno.Message.class)).intercept(MethodDelegation.to(clazz));
		
		Unloaded<? extends Object> unloadedClass = builder.make();
		Loaded<?> loaded = unloadedClass.load(getClass().getClassLoader());
		Class<?> dynamicType = loaded.getLoaded();
		Object o = dynamicType.newInstance();
		
		return o;	
	}
}