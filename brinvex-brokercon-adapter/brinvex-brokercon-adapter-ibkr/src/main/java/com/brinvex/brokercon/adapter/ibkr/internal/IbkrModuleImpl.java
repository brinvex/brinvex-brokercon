package com.brinvex.brokercon.adapter.ibkr.internal;

import com.brinvex.brokercon.adapter.ibkr.api.IbkrModule;
import com.brinvex.brokercon.adapter.ibkr.api.service.IbkrDms;
import com.brinvex.brokercon.adapter.ibkr.api.service.IbkrFetcher;
import com.brinvex.brokercon.adapter.ibkr.api.service.IbkrFinTransactionMapper;
import com.brinvex.brokercon.adapter.ibkr.api.service.IbkrPtfActivityProvider;
import com.brinvex.brokercon.adapter.ibkr.api.service.IbkrStatementMerger;
import com.brinvex.brokercon.adapter.ibkr.api.service.IbkrStatementParser;
import com.brinvex.brokercon.adapter.ibkr.internal.service.IbkrDmsImpl;
import com.brinvex.brokercon.adapter.ibkr.internal.service.IbkrFetcherImpl;
import com.brinvex.brokercon.adapter.ibkr.internal.service.IbkrFinTransactionMapperImpl;
import com.brinvex.brokercon.adapter.ibkr.internal.service.IbkrPtfActivityProviderImpl;
import com.brinvex.brokercon.adapter.ibkr.internal.service.IbkrStatementMergerImpl;
import com.brinvex.brokercon.adapter.ibkr.internal.service.IbkrStatementParserImpl;
import com.brinvex.brokercon.core.api.Module;
import com.brinvex.brokercon.core.api.ModuleContext;
import com.brinvex.brokercon.core.api.ModuleFactory;
import com.brinvex.brokercon.core.api.provider.Provider;
import com.brinvex.brokercon.core.api.provider.PtfActivityProvider;

import java.util.List;
import java.util.SequencedCollection;
import java.util.Set;

import static java.util.Collections.emptyList;

public class IbkrModuleImpl implements IbkrModule, Module {

    public static class IbkrModuleFactory implements ModuleFactory<IbkrModule> {

        @Override
        public Class<IbkrModule> connectorType() {
            return IbkrModule.class;
        }

        @Override
        public Set<Class<? extends Provider<?, ?>>> providerTypes() {
            return Set.of(PtfActivityProvider.class);
        }

        @Override
        public IbkrModule createConnector(ModuleContext moduleCtx) {
            return new IbkrModuleImpl(moduleCtx);
        }
    }

    private final ModuleContext moduleCtx;

    public IbkrModuleImpl(ModuleContext moduleCtx) {
        this.moduleCtx = moduleCtx;
    }

    @Override
    public SequencedCollection<Provider<?, ?>> providers(Class<? extends Provider<?, ?>> providerType) {
        if (providerType == PtfActivityProvider.class) {
            return List.of(ptfProgressProvider());
        }
        return emptyList();
    }

    @Override
    public IbkrStatementMerger statementMerger() {
        return moduleCtx.singletonService(IbkrStatementMerger.class, IbkrStatementMergerImpl::new);
    }

    @Override
    public IbkrStatementParser statementParser() {
        return moduleCtx.singletonService(IbkrStatementParser.class, IbkrStatementParserImpl::new);
    }

    @Override
    public IbkrFinTransactionMapper finTransactionMapper() {
        return moduleCtx.singletonService(IbkrFinTransactionMapper.class, IbkrFinTransactionMapperImpl::new);
    }

    @Override
    public IbkrDms dms() {
        return moduleCtx.singletonService(IbkrDms.class, () -> new IbkrDmsImpl(moduleCtx.dms()));
    }

    @Override
    public IbkrFetcher fetcher() {
        return moduleCtx.singletonService(IbkrFetcher.class, IbkrFetcherImpl::new);
    }

    @Override
    public IbkrPtfActivityProvider ptfProgressProvider() {
        return moduleCtx.singletonService(IbkrPtfActivityProvider.class, () -> new IbkrPtfActivityProviderImpl(
                dms(),
                statementParser(),
                fetcher(),
                statementMerger(),
                finTransactionMapper()
        ));
    }
}
