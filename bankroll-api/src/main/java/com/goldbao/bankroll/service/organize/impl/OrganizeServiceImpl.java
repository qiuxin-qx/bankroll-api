package com.goldbao.bankroll.service.organize.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.goldbao.bankroll.dao.organize.OrganizeDao;
import com.goldbao.bankroll.model.Organize;
import com.goldbao.bankroll.service.organize.OrganizeService;

public class OrganizeServiceImpl implements OrganizeService {

	@Autowired
	private OrganizeDao organizeDao;
	
	@Override
	public Organize addOrgianze(Organize organize) {
		return organizeDao.addOrganize(organize);
	}

	@Override
	public Organize getOrganizeById(Long id) {
		return organizeDao.getOrganizeById(id);
	}

	@Override
	public Organize updateOrganize(Organize organize) {
		return organizeDao.updateOrganize(organize);
	}

}
