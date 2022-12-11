package com.common.reflection;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

public class ClassDetailsByReflection {

	/**
	 * set ENUM, PRIMITIVE, NON PRIMITIVE and USER DEFIEND data type values AND skip
	 * null values means if in source object any field value is null then not set in
	 * target object field And if any field return type is collection Type like
	 * (List, Map, Set ) then set directly to target object
	 * 
	 * @param <S> source Object
	 * @param <T> target Object
	 * @return <T> Updated target Object
	 */
	public static <S, T> T setWithoutNullValuesFromDTO(S source, T target) throws Exception {

		if (source == null || target == null) {
			return null;
		}

		Class classSource = source.getClass();
		Class classTarget = target.getClass();

		Method[] declaredMethodsOfSource = classSource.getDeclaredMethods();
		Method[] declaredMethodsOfTarget = classTarget.getDeclaredMethods();

		for (Method methodOfSource : declaredMethodsOfSource) {

			for (Method methodOfTarget : declaredMethodsOfTarget) {

				if (methodOfSource.getName().equals(methodOfTarget.getName())) {

					String methodName = methodOfSource.getName();
					String subStringValue = methodName.substring(3);
					String methodType = methodName.substring(0, 3);

					if (!methodType.equals("set")) {

						Field[] fields = classSource.getDeclaredFields();

						for (Field field : fields) {

							if (field.getName().equalsIgnoreCase(subStringValue)) {

								Class<?> returnTypeOfMethod = methodOfSource.getReturnType();
								Boolean primitive = returnTypeOfMethod.isPrimitive();
								Boolean enum1 = returnTypeOfMethod.isEnum();
								String packageName = returnTypeOfMethod.getName();

								if (primitive.equals(Boolean.FALSE) && enum1.equals(Boolean.FALSE)
										&& !Collection.class.isAssignableFrom(returnTypeOfMethod)) {

									String subStringMethodName = packageName.substring(0, 9);

									if (subStringMethodName.equals("java.lang")) {

										PropertyDescriptor sourceProperty = new PropertyDescriptor(subStringValue,
												classSource);
										Method getter = sourceProperty.getReadMethod();
										Object getterValue = getter.invoke(source);

										PropertyDescriptor targetProperty = new PropertyDescriptor(subStringValue,
												classTarget);
										Method setter = targetProperty.getWriteMethod();

										if (getterValue != null) {
											setter.invoke(target, getterValue);
										}

									} else {

										PropertyDescriptor sourceProperty = new PropertyDescriptor(subStringValue,
												classSource);
										Method getterSource = sourceProperty.getReadMethod();
										Object sourceObj = getterSource.invoke(source);

										PropertyDescriptor targetProperty = new PropertyDescriptor(subStringValue,
												classTarget);
										Method getterTarget = targetProperty.getReadMethod();
										Method setterTarget = targetProperty.getWriteMethod();
										Object targetObj = getterTarget.invoke(target);

										if (targetObj != null) {

											setWithoutNullValuesFromDTO(sourceObj, targetObj);

										} else {

											setterTarget.invoke(target, sourceObj);
										}
									}

								} else {

									PropertyDescriptor sourceProperty = new PropertyDescriptor(subStringValue,
											classSource);
									PropertyDescriptor targetProperty = new PropertyDescriptor(subStringValue,
											classTarget);

									Method getter = sourceProperty.getReadMethod();
									Object sourceGetterMethodValue = getter.invoke(source);

									Method setter = targetProperty.getWriteMethod();

									if (sourceGetterMethodValue != null) {
										setter.invoke(target, sourceGetterMethodValue);
									}

								}

							}
						}
					}
				}
			}

		}

		return target;

	}

}
