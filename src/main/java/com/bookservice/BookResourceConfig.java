package com.bookservice;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/v1/*")
public class BookResourceConfig extends ResourceConfig
{
	public BookResourceConfig()
	{
		packages("com.bookservice.resource");
	}
}
