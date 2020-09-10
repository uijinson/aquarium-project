package kr.pe.aqua.exception;

public class FishNotFoundException extends RuntimeException{
public FishNotFoundException(Long id) {
	super("Could not find fish" + id);
}
}
