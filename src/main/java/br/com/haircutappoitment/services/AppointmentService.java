package br.com.haircutappoitment.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.haircutappoitment.exceptions.NotFoundException;
import br.com.haircutappoitment.models.enums.AppointmentStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.haircutappoitment.models.dtos.appointment.AppointmentCreateDto;
import br.com.haircutappoitment.models.dtos.appointment.AppointmentDto;
import br.com.haircutappoitment.models.entities.AppointmentEntity;
import br.com.haircutappoitment.repositories.AppointmentRepository;

@Service
public class AppointmentService {
    
    @Autowired
    private final AppointmentRepository appointmentRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<AppointmentDto> findAllAppointments(){
        List<AppointmentEntity> appointmentEntity = appointmentRepository.findAll();
        return appointmentEntity.stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }

    public Page<AppointmentDto> findAllPageable(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<AppointmentEntity> appointmentEntities = appointmentRepository.findAllBy(pageable);

        return appointmentEntities.map(this::convertEntityToDto);
    }

    public AppointmentDto findAppointmentById(Long id){
        AppointmentEntity appointmentEntity = findByIdEntity(id);
        return convertEntityToDto(appointmentEntity);
    }

    public AppointmentCreateDto createAppointment(AppointmentCreateDto appointmentCreateDto){
        AppointmentEntity appointmentEntity = convertCreateDtoToEntity(appointmentCreateDto);
        appointmentEntity.setTask(appointmentCreateDto.getTask());
        appointmentEntity.setUser(appointmentCreateDto.getUser());
        appointmentEntity.setStatus(AppointmentStatus.RESERVED);

        appointmentRepository.save(appointmentEntity);
        return convertEntityToCreateDto(appointmentEntity);
    }

    public AppointmentCreateDto updateAppointment(Long id, AppointmentCreateDto appointmentCreateDto){
        AppointmentEntity appointmentEntity = findByIdEntity(id);
        appointmentEntity.setDate(LocalDate.now()); // ToDo melhorar update
        appointmentRepository.save(appointmentEntity);
        return convertEntityToCreateDto(appointmentEntity);
    }

    public AppointmentDto cancelAppointment(Long id) {
        return updateAppointmentStatus(id, AppointmentStatus.CANCELED);
    }

    public AppointmentDto finishAppointment(Long id) {
        return updateAppointmentStatus(id, AppointmentStatus.DONE);
    }

    public void deleteAppointment(Long id){
        AppointmentEntity appointmentEntity = findByIdEntity(id);
        appointmentRepository.delete(appointmentEntity);
    }

    private AppointmentDto updateAppointmentStatus(Long id, AppointmentStatus newStatus) {
        AppointmentEntity appointmentEntity = findByIdEntity(id);
        appointmentEntity.setStatus(newStatus);
        return convertEntityToDto(appointmentEntity);
    }

    private AppointmentEntity findByIdEntity(Long id){
        //ToDo error message
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find appointment with id:" + id));
    }

    private AppointmentEntity convertDtoToEntity(AppointmentDto appointmentDto){
        return modelMapper.map(appointmentDto, AppointmentEntity.class);
    }

    private AppointmentDto convertEntityToDto(AppointmentEntity appointmentEntity){
        return modelMapper.map(appointmentEntity, AppointmentDto.class);
    }

    private AppointmentEntity convertCreateDtoToEntity(AppointmentCreateDto appointmentCreateDto){
        return modelMapper.map(appointmentCreateDto, AppointmentEntity.class);
    }

    private AppointmentCreateDto convertEntityToCreateDto(AppointmentEntity appointmentEntity){
        return modelMapper.map(appointmentEntity, AppointmentCreateDto.class);
    }
}