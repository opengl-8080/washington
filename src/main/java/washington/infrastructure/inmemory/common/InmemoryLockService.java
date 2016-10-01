package washington.infrastructure.inmemory.common;

import washington.domain.common.Lock;
import washington.domain.common.LockService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InmemoryLockService implements LockService {

    @Override
    public void lock(Lock lock) {
        // no lock
    }
}
