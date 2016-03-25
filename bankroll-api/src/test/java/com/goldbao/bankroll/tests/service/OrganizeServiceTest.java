package com.goldbao.bankroll.tests.service;


import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goldbao.bankroll.model.Organize;
import com.goldbao.bankroll.model.enums.EnumDr;
import com.goldbao.bankroll.service.organize.OrganizeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml" })
public class OrganizeServiceTest {
	
	private Logger log = org.slf4j.LoggerFactory.getLogger(OrganizeServiceTest.class);
	
	@Autowired
	private OrganizeService organizeService;
	
	@Test
	public void testAddOrganize() {
		log.info("save organize");
		
		Organize organize = new Organize();
		organize.setOrgName("org1");
		organize.setDr(EnumDr.NORMAL);
		Organize result = organizeService.addOrgianze(organize);
		
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testUpdateOrganize() {
		log.info("update organize");
		
		Organize organize = organizeService.getOrganizeById(new Long(1L));
		if (organize != null) {
			log.info("orgianze id is" +  organize.getId());
			organize.setLastUpdateTime(new Date());
			organize = organizeService.updateOrganize(organize);
		}
	}

	@Test
	public void testGetOrganizeById() {
		log.info("get organize by orgid");
		Organize organize = organizeService.getOrganizeById(new Long(1L));
		Assert.assertNotNull(organize);
	}
}
