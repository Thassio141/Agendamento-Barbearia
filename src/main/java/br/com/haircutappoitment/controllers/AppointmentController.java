package br.com.haircutappoitment.controllers;

import br.com.haircutappoitment.exceptions.BadRequestException;
import br.com.haircutappoitment.exceptions.InternalServerErrorException;
import br.com.haircutappoitment.domain.dtos.appointment.AppointmentCreateDto;
import br.com.haircutappoitment.domain.dtos.appointment.AppointmentDto;
import br.com.haircutappoitment.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService)
            throws BadRequestException, InternalServerErrorException {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> findAllAppointments()
            throws BadRequestException, InternalServerErrorException{
        return new ResponseEntity<>(appointmentService.findAllAppointments(), HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<AppointmentDto>> findaAllPageable(@RequestParam Integer page, @RequestParam Integer size)
            throws BadRequestException, InternalServerErrorException{
        return new ResponseEntity<>(appointmentService.findAllPageable(page,size),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> findAppointmentById(@PathVariable Long id)
            throws BadRequestException, InternalServerErrorException{
        return new ResponseEntity<>(appointmentService.findAppointmentById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentCreateDto> createAppointment(@RequestBody AppointmentCreateDto appointmentCreateDto)
            throws BadRequestException, InternalServerErrorException{
        return new ResponseEntity<>(appointmentService.createAppointment(appointmentCreateDto),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AppointmentCreateDto> updateAppointment(@PathVariable Long id, AppointmentCreateDto appointmentCreateDto)
            throws BadRequestException, InternalServerErrorException{
        return new ResponseEntity<>(appointmentService.updateAppointment(id,appointmentCreateDto),HttpStatus.OK);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<AppointmentDto> cancelAppointment(@PathVariable Long id)
            throws BadRequestException, InternalServerErrorException{
        return new ResponseEntity<>(appointmentService.cancelAppointment(id),HttpStatus.OK);
    }

    @PutMapping("/finish/{id}")
    public ResponseEntity<AppointmentDto> finishAppointment(@PathVariable Long id)
            throws BadRequestException, InternalServerErrorException{
        return new ResponseEntity<>(appointmentService.finishAppointment(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id)
            throws BadRequestException, InternalServerErrorException{
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
