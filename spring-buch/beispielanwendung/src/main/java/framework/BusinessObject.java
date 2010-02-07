package framework;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Iterator;

public class BusinessObject implements Serializable {

    protected Iterator getFieldIterator() {
        return new Iterator() {

            int index = 0;

            Field[] field = null;

            protected Field[] getField() {
                if (field == null) {
                    Class clazz = BusinessObject.this.getClass();
                    field = clazz.getDeclaredFields();
                }
                return field;
            }

            public boolean hasNext() {
                return (getField().length > index);
            }

            public Object next() {
                Field result = getField()[index++];
                result.setAccessible(true);
                return result;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer();
        Class clazz = getClass();
        sb.append('[');
        sb.append(clazz.getName());
        Iterator fieldIter = getFieldIterator();
        while (fieldIter.hasNext()) {
            sb.append('\n');
            try {
                Field field = (Field) fieldIter.next();
                field.setAccessible(true);
                sb.append(field.getName());
                sb.append('=');
                Object fieldValue = field.get(this);
                if (fieldValue != null) {
                    sb.append(fieldValue.toString());
                } else {
                    sb.append("null");
                }
            } catch (IllegalArgumentException e) {
                sb.append("??IllegalArgument");
            } catch (IllegalAccessException e) {
                sb.append("??IllegalAccess");
            }
        }
        sb.append(']');
        sb.append('\n');
        return sb.toString();
    }

    public boolean equals(Object other) {
        if (!other.getClass().equals(this.getClass())) {
            return false;
        }
        Iterator fieldIter = getFieldIterator();
        while (fieldIter.hasNext()) {
            Field field = (Field) fieldIter.next();
            try {
                if (!field.get(this).equals(field.get(other))) {
                    return false;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public int hashCode() {
        int result = 0;
        Iterator fieldIter = getFieldIterator();
        while (fieldIter.hasNext()) {
            Field field = (Field) fieldIter.next();
            try {
                result += field.get(this).hashCode();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}