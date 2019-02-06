package uy.com.geocom.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.geocom.dto.MetadataNotificationDTO;
import uy.com.geocom.dto.NotificationQueryDTO;

public class NotificationRepositoryCustomImpl implements NotificationRepositoryCustom{

	@PersistenceContext
	private EntityManager em;

	@Override
	public MetadataNotificationDTO findBylocalOrChannel(List<Integer> locals, List<Integer> channels, Boolean enabled, Integer page, Integer max, String order, String field) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();

		String str = "select new uy.com.geocom.dto.NotificationQueryDTO(n.notificationId, n.title, n.message, n.validity, lo.local, n.enabled, ch.channel) "
				+ "from NotificationLocal nlo, Notification n, Local lo, NotificationChannel nch, Channel ch  where n = nlo.notification "
				+ "and lo = nlo.local and n = nch.notification and ch = nch.channel";

		String str2 = "select count(n) from NotificationLocal nlo, Notification n, Local lo, NotificationChannel nch, Channel ch  where n = nlo.notification "
				+ "and lo = nlo.local and n = nch.notification and ch = nch.channel";

		sb.append(str);
		sb2.append(str2);

		if (!locals.isEmpty()) {
			sb.append(" and lo.localId in :locals");
			sb2.append(" and lo.localId in :locals");
		}

		if (!channels.isEmpty()) {
			sb.append(" and ch.channelId in :channels");
			sb2.append(" and ch.channelId in :channels");
		}

		if (enabled != null) {
			sb.append(" and n.enabled = :enabled");
			sb2.append(" and n.enabled = :enabled");
		}

		if (order != null && field != null) {
			switch (field) {
			case "title":
				sb.append(" order by n.title "+order);
				break;
			case "channel":
				sb.append(" order by ch.channel "+order);
				break;
			case "local":
				sb.append(" order by lo.local "+order);
				break;
			case "validity":
				sb.append(" order by n.validity "+order);
				break;
			case "enabled":
				sb.append(" order by n.enabled "+order);
				break;
			case "created":
				sb.append(" order by n.created "+order);
				break;
			}
		} else {
			sb.append(" order by n.created desc");
		}

		Query query = em.createQuery(sb.toString());
		Query query2 = em.createQuery(sb2.toString());
		if (!locals.isEmpty()) {
			query.setParameter("locals", locals);
			query2.setParameter("locals", locals);
		}

		if (!channels.isEmpty()) {
			query.setParameter("channels", channels);
			query2.setParameter("channels", channels);
		}

		if (enabled != null) {
			query.setParameter("enabled", enabled);
			query2.setParameter("enabled", enabled);
		}

		long count= (Long) query2.getSingleResult(); 

		if (page != null && max != null) {
			query.setFirstResult((page - 1) * max);
			query.setMaxResults(max);
		}

		List<NotificationQueryDTO> data = query.getResultList();

		MetadataNotificationDTO metadata = new MetadataNotificationDTO(count, data);

		return metadata;
	}

}
