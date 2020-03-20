/**
 * 
 */
package com.usa.nj.gov.uhip.co.model;

import java.util.Date;

import lombok.Data;

/**
 * @author vinay
 *
 */

@Data
public class CoBatchRunDetailsModel {

	Integer runSeq;

	String batchName;

	Date startDate;

	Date endDate;

	private String batchRunStatus;

	private Integer instanceNum;

}
