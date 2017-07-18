package cn.ffcs.annotation;

import java.lang.annotation.*;

/**
 * Created by MәӧωρaЯsε on 2017/6/26.
 *
 */

@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ULog {
}
