package org.frame.shiro.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;


public class RedisSessionDAO extends AbstractSessionDAO {
	private static Logger logger = LoggerFactory
			.getLogger(RedisSessionDAO.class);

	private RedisManager redisManager;

	// shiro-redis的session对象前缀
	private String keyPrefix = "shiro_session:";

	private void saveSession(Session session) throws UnknownSessionException {
		System.out.println("----saveSession---" + session.getId());
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}
		byte[] key = getByteKey(session.getId());
		byte[] value = SerializationUtils.serialize(session);
		// session.setTimeout(redisManager.getExpire()*1000);
		this.getRedisManager().set(key, value,Integer.parseInt(session.getTimeout()+""));

	}

	@Override
	public void delete(Session session) {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}
		getRedisManager().del(this.getByteKey(session.getId()));
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();
		
		Set<byte[]> keys = getRedisManager().keys(this.keyPrefix + "*");
		if (keys != null && keys.size() > 0) {
			for (byte[] key : keys) {
				Session s = (Session) SerializationUtils
						.deserialize(getRedisManager().get(key));
				sessions.add(s);
			}
		}
		return sessions;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
			logger.error("session id is null");
			return null;
		}

		Session s = (Session) SerializationUtils.deserialize(getRedisManager().get(this.getByteKey(sessionId)));
		return s;
	}

	//获得byte[]型的key
	private byte[] getByteKey(Serializable sessionId) {
		String preKey = this.keyPrefix + sessionId;
		return preKey.getBytes();
	}

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

}
