package com.airline.core.currency;


/**
 * Base exception for currency violations.
 */
public class CurrencyException extends RuntimeException
{
    private static final long serialVersionUID = -6737139477011503232L;

    /** Constructs a new Currency exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public CurrencyException()
    {
        this( null, null );
    }


    /** Constructs a new currency exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public CurrencyException( final String message )
    {
        this( message, null );
    }


    /**
     * Constructs a new currency exception with the specified detail message and
     * cause.
     *
     * <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since 0.0
     */
    public CurrencyException( final String message, final Throwable cause )
    {
        this( message, cause, false, true );
    }


    /** Constructs a new currency exception with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since 0.0
     */
    public CurrencyException( final Throwable cause )
    {
        this( null, cause );
    }


    /**
     * Constructs a new runtime exception with the specified detail
     * message, cause, suppression enabled or disabled, and writable
     * stack trace enabled or disabled.
     *
     * @param message the detail message.
     * @param cause the cause.  (A {@code null} value is permitted,
     * and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression whether or not suppression is enabled
     *                          or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     *
     * @since 0.0
     */
    protected CurrencyException(  final String     message
                                , final Throwable  cause
                                , final boolean    enableSupression
                                , final boolean    writeStackTrace )
    {
        super( message, cause, enableSupression, writeStackTrace );
    }


}