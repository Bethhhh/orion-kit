package com.orion.test.reflect;

import com.orion.lang.Console;
import com.orion.test.reflect.value.User;
import com.orion.utils.reflect.Fields;
import com.orion.utils.reflect.Methods;
import org.junit.Test;

/**
 * @author ljh15
 * @version 1.0.0
 * @since 2020/5/13 18:39
 */
public class FieldsTests {

    @Test
    public void getTests() {
        System.out.println(Fields.getFieldNameByMethodName("getName"));
        System.out.println(Fields.getFieldNameByMethodName("setName"));
        System.out.println(Fields.getFieldNameByMethodName("isName"));
        System.out.println(Fields.getFieldByMethod(User.class, Methods.getAccessibleMethod(User.class, "getId")));
        System.out.println(Fields.getFieldByMethodName(User.class, "getId"));
        System.out.println("------");
        System.out.println((Object) Fields.getFieldValue(new User(1L), "id"));
        System.out.println((Object) Fields.getFieldValue(new User(2L), Fields.getAccessibleField(User.class, "id")));
    }

    @Test
    public void setTests() {
        User user = new User();
        Fields.setFieldValue(user, "id", 1L);
        System.out.println(user.getId());
        Fields.setFieldValue(user, Fields.getAccessibleField(user.getClass(), "id"), 2L);
        System.out.println(user.getId());
        Fields.setFieldValueInfer(user, "id", "2");
        System.out.println(user.getId());
        Fields.setFieldValueInfer(user, Fields.getAccessibleField(user.getClass(), "id"), 3);
        System.out.println(user.getId());
    }

    @Test
    public void getFieldTests() {
        System.out.println("------");
        Fields.getFieldList(User.class).forEach(Console::trace);
        System.out.println("------");
        Fields.getFieldMap(User.class).forEach(Console::trace);
        System.out.println("------");
        Fields.getStaticFields(User.class).forEach(Console::trace);
    }

}
