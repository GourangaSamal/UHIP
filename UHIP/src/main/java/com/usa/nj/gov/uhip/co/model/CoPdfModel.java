/**
 * 
 */
package com.usa.nj.gov.uhip.co.model;

import lombok.Data;

/**
 * @author vinay
 *
 */

@Data
public class CoPdfModel {

	Integer coPdfId;

	String caseNumber;

	byte[] pdfDocument;

	String planName;

	String PlanStatus;

}// CoPdfEntity
