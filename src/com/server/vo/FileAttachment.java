package com.server.vo;

import java.io.InputStream;




@SuppressWarnings("serial")
public class FileAttachment extends AbstractObjectValues{

	
		/**
		 * Default constructor
		 */
		public FileAttachment() {

		}
		/**
		 * Default constructor
		 */
		public FileAttachment(String fileId, String notes, String fileName, String contentType, InputStream stream) {
			this.fileId = fileId;
			this.notes = notes;
			this.stream = stream;
			this.fileName = fileName;
			this.contentType = contentType;
		}
		
		/**
		 * Type of the FileAttachment
		 */
		private String fileId;
		private String notes;
		private String fileName;
		private String contentType;
		private InputStream stream;

		
	
		
		public String getFileId() {
			return fileId;
		}
		public void setFileId(String fileId) {
			this.fileId = fileId;
		}
		/**
		 * @return the stream
		 */
		public InputStream getStream() {
			return stream;
		}
		/**
		 * @param stream the stream to set
		 */
		public void setStream(InputStream stream) {
			this.stream = stream;
		}
		/**
		 * @return the fileName
		 */
		public String getFileName() {
			return fileName;
		}
		/**
		 * @param fileName the fileName to set
		 */
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		/**
		 * @return the contentType
		 */
		public String getContentType() {
			return contentType;
		}
		/**
		 * @param contentType the contentType to set
		 */
		public void setContentType(String contentType) {
			this.contentType = contentType;
		}
		/**
		 * @return the notes
		 */
		public String getNotes() {
			return notes;
		}
		/**
		 * @param notes the notes to set
		 */
		public void setNotes(String notes) {
			this.notes = notes;
		}

		

		
}