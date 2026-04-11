package com.api.pojo;
import java.util.List;
import com.api.pojo.request.model.Customer;
import com.api.pojo.request.model.CustomerAddress;
import com.api.pojo.request.model.CustomerProduct;



public record CreateJobPayload(int mst_service_location_id, int mst_platform_id, int mst_warrenty_status_id,
		int mst_oem_id, Customer customer, 
		CustomerAddress customer_address,
		CustomerProduct customer_product,
		List<Problems> problems
		) 
{
	
}