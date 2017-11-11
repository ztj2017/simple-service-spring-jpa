package com.bookservice.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookservice.service.BookService;
import com.common.entity.Book;
import com.common.entity.Books;

@Path("books")
public class BookResource
{
	private static final Logger LOGGER = Logger.getLogger(BookResource.class);
	
	@Autowired
	private BookService bookService;
	
	/**
	 * <p>
	 * getBooks.
	 * </p>
	 * 
	 * @return a {@link com.example.domain.Books} object.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Books getBooks()
	{
		final Books books = bookService.getBooks();
		BookResource.LOGGER.debug(books);
		return books;
	}
	
	/**
	 * <p>
	 * getBookByPath.
	 * </p>
	 * 
	 * @param bookId
	 *            a {@link java.lang.Integer} object.
	 * @return a {@link com.example.domain.Book} object.
	 */
	@Path("{bookId:[0-9]*}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Book getBookByPath(@PathParam("bookId") final Long bookId)
	{
		final Book book = bookService.getBook(bookId);
		BookResource.LOGGER.debug(book);
		return book;
	}
	
	/**
	 * <p>
	 * getBookByQuery.
	 * </p>
	 * 
	 * @param bookId
	 *            a {@link java.lang.Integer} object.
	 * @return a {@link com.example.domain.Book} object.
	 */
	@Path("/book")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getBookByQuery(@QueryParam("id") final Long bookId)
	{
		final Book book = bookService.getBook(bookId);
		ResponseBuilder builder = Response.ok(book);
		builder.header("Access-Control-Allow-Origin", "*");
		builder.header("Access-Control-Allow-Methods", "GET,POST");
		return builder.build();
	}
	
	/**
	 * <p>
	 * saveBook.
	 * </p>
	 * 
	 * @param book
	 *            a {@link com.example.domain.Book} object.
	 * @return a {@link com.example.domain.Book} object.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.TEXT_XML })
	public Book saveBook(final Book book)
	{
		return bookService.saveBook(book);
	}
	
	/**
	 * <p>
	 * updateBook.
	 * </p>
	 * 
	 * @param bookId
	 *            a {@link java.lang.Integer} object.
	 * @param book
	 *            a {@link com.example.domain.Book} object.
	 * @return a {@link com.example.domain.Book} object.
	 */
	@Path("{bookId:[0-9]*}")
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.TEXT_XML })
	public Book updateBook(@PathParam("bookId") final Long bookId,
			final Book book)
	{
		if (book == null)
		{
			return null;
		}
		return bookService.updateBook(bookId, book);
	}
	
	/**
	 * <p>
	 * deleteBook.
	 * </p>
	 * 
	 * @param bookId
	 *            a {@link java.lang.Integer} object.
	 * @return a {@link java.lang.String} object.
	 */
	@Path("/{bookId:[0-9]*}")
	@DELETE
	public String deleteBook(@PathParam("bookId") final Long bookId)
	{
		if (bookService.deleteBook(bookId))
		{
			return "Deleted book id=" + bookId;
		} else
		{
			return "Deleted book failed id=" + bookId;
		}
	}
}
