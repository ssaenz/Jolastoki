package eu.lapecera.jolastoki.domain;

public enum SoundTrack {
	
	CLICK 			("seleccion.mp3"),
	OK				("acierto.mp3"),
	KO				("fallo.mp3"),
	BACKGROUND		("musica_fondo.mp3"),
	MOTIVATION		("motivacion.mp3");
	
	private String fileName;
	
	private SoundTrack (String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
}
