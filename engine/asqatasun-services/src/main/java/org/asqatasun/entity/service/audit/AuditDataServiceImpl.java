/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.entity.service.audit;

import java.util.Collection;
import java.util.Date;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.audit.factory.AuditFactory;
import org.asqatasun.entity.dao.audit.AuditDAO;
import org.asqatasun.entity.service.AbstractGenericDataService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author jkowalczyk
 */
@Service("auditDataService")
public class AuditDataServiceImpl extends AbstractGenericDataService<Audit, Long> implements AuditDataService {

    public AuditDataServiceImpl() {
        super();
    }

    @Override
    public Audit create(Date date) {
        return ((AuditFactory) entityFactory).create(date);
    }

    @Override
    public Collection<Audit> findAll(AuditStatus status) {
        return ((AuditDAO) entityDao).findAll(status);
    }

    @Override
    public Audit getAuditWithTest(Long id) {
        return ((AuditDAO) entityDao).findAuditWithTest(id);
    }

}
