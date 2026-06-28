package com.wsdev.simplestock.utilities;

public class Validator
{
    public static String requiredNonNull( Object value, IllegalArgumentException argumentException ) throws Exception
    {
        if( value == null )
        {
            return  argumentException.getMessage();
        }

        return value.toString();
    }
}
