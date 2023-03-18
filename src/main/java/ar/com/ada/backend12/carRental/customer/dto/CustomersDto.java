package ar.com.ada.backend12.carRental.customer.dto;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;

import java.util.List;

public class CustomersDto implements ApiReturnable {
    private List<CustomerDto> customersDto;

    public CustomersDto() {
    }

    public CustomersDto(List<CustomerDto> customersDto) {
        this.customersDto = customersDto;
    }

    public List<CustomerDto> getCustomersDto() {
        return customersDto;
    }

    public void setCustomersDto(List<CustomerDto> customersDto) {
        this.customersDto = customersDto;
    }
}
