package com.airline.core.currency;

public class CurrencyException extends RuntimeException
{

    /**
     *
     */
    private static final long serialVersionUID = -6737139477011503232L;

    public CurrencyException()
    {
        this( null, null );
    }


    public CurrencyException(String message)
    {
        this( message, null );
    }


    public CurrencyException( String message, Throwable cause )
    {
        this( message, cause, false, true );
    }


    protected CurrencyException(String message, Throwable cause, boolean enableSupression, boolean writeStackTrace )
    {
        super( message, cause, enableSupression, writeStackTrace );
    }

    public CurrencyException(Throwable cause)
    {
        this( null, cause );
    }

}