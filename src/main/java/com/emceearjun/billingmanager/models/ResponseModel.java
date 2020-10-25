package com.emceearjun.billingmanager.models;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseModel<T> {
	private HttpStatus status;
	private boolean success;
	private String message;
	private Timestamp timestamp;
	private T payload;

	public ResponseModel() {
		this.status = null;
		this.success = false;
		this.message = null;
		this.timestamp = null;
		this.payload = null;
	}

	public ResponseModel(HttpStatus status, boolean success, String message, T payload) {
		super();
		this.status = status;
		this.success = success;
		this.message = message;
		this.timestamp = new Timestamp(System.currentTimeMillis());
		this.payload = payload;
	}

	public ResponseModel<T> setStatus(HttpStatus status) {
		this.status = status;
		return this;
	}

	public ResponseModel<T> setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public ResponseModel<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public ResponseModel<T> setPayload(T payload) {
		this.payload = payload;
		return this;
	}

	public ResponseEntity<ResponseModel<T>> build() {
		ResponseModel<T> response = new ResponseModel<T>(status, success, message, payload);
		return ResponseEntity.status(this.status).body(response);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public boolean getSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public T getPayload() {
		return payload;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}
}
