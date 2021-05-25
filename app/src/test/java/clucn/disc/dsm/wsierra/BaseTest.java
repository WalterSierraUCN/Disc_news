/*
 * Copyright (c) 2021. Walter Sierra Vega
 */

package clucn.disc.dsm.wsierra;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class BaseTest {
    /**
     *
     * @param obj to transform
     * @return the String view of the object
     */
    protected static String toString(final Object obj){
        return ToStringBuilder.reflectionToString(obj, ToStringStyle.MULTI_LINE_STYLE);
    }
}
